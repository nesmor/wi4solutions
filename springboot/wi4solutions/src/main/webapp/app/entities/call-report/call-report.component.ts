import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICallReport } from 'app/shared/model/call-report.model';
import { Principal } from 'app/core';
import { CallReportService } from './call-report.service';

@Component({
    selector: 'jhi-call-report',
    templateUrl: './call-report.component.html'
})
export class CallReportComponent implements OnInit, OnDestroy {
    callReports: ICallReport[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private callReportService: CallReportService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.callReportService.query().subscribe(
            (res: HttpResponse<ICallReport[]>) => {
                this.callReports = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCallReports();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICallReport) {
        return item.id;
    }

    registerChangeInCallReports() {
        this.eventSubscriber = this.eventManager.subscribe('callReportListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
