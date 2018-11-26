import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDialPlan } from 'app/shared/model/dial-plan.model';
import { Principal } from 'app/core';
import { DialPlanService } from './dial-plan.service';

@Component({
    selector: 'jhi-dial-plan',
    templateUrl: './dial-plan.component.html'
})
export class DialPlanComponent implements OnInit, OnDestroy {
    dialPlans: IDialPlan[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private dialPlanService: DialPlanService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.dialPlanService.query().subscribe(
            (res: HttpResponse<IDialPlan[]>) => {
                this.dialPlans = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDialPlans();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDialPlan) {
        return item.id;
    }

    registerChangeInDialPlans() {
        this.eventSubscriber = this.eventManager.subscribe('dialPlanListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
