import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription, timer, pipe } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IActiveCall } from 'app/shared/model/active-call.model';
import { Principal } from 'app/core';
import { ActiveCallService } from './active-call.service';

@Component({
    selector: 'jhi-active-call',
    templateUrl: './active-call.component.html',
    styles: ['.table-responsive { font-size: 80%; }']
})
export class ActiveCallComponent implements OnInit, OnDestroy {
    activeCalls: IActiveCall[];
    currentAccount: any;
    eventSubscriber: Subscription;
    interval: any;

    constructor(
        private activeCallService: ActiveCallService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.activeCallService.query().subscribe(
            (res: HttpResponse<IActiveCall[]>) => {
                this.activeCalls = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.interval = setInterval(() => {
            this.loadAll();
        }, 1000);

        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInActiveCalls();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
        clearInterval(this.interval);
    }

    trackId(index: number, item: IActiveCall) {
        return item.id;
    }

    registerChangeInActiveCalls() {
        this.eventSubscriber = this.eventManager.subscribe('activeCallListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
