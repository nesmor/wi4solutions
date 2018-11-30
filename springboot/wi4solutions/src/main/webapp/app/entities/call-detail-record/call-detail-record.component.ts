import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICallDetailRecord } from 'app/shared/model/call-detail-record.model';
import { Principal } from 'app/core';
import { CallDetailRecordService } from './call-detail-record.service';

@Component({
    selector: 'jhi-call-detail-record',
    templateUrl: './call-detail-record.component.html'
})
export class CallDetailRecordComponent implements OnInit, OnDestroy {
    callDetailRecords: ICallDetailRecord[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private callDetailRecordService: CallDetailRecordService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.callDetailRecordService.query().subscribe(
            (res: HttpResponse<ICallDetailRecord[]>) => {
                this.callDetailRecords = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCallDetailRecords();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICallDetailRecord) {
        return item.id;
    }

    registerChangeInCallDetailRecords() {
        this.eventSubscriber = this.eventManager.subscribe('callDetailRecordListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
