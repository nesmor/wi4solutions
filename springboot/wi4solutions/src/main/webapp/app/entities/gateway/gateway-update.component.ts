import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IGateway } from 'app/shared/model/gateway.model';
import { GatewayService } from './gateway.service';
import { IDialPlan } from 'app/shared/model/dial-plan.model';
import { DialPlanService } from 'app/entities/dial-plan';

@Component({
    selector: 'jhi-gateway-update',
    templateUrl: './gateway-update.component.html'
})
export class GatewayUpdateComponent implements OnInit {
    gateway: IGateway;
    isSaving: boolean;

    dialplans: IDialPlan[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private gatewayService: GatewayService,
        private dialPlanService: DialPlanService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ gateway }) => {
            this.gateway = gateway;
        });
        this.dialPlanService.query().subscribe(
            (res: HttpResponse<IDialPlan[]>) => {
                this.dialplans = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.gateway.nat = 'force_rport';
        this.gateway.username = this.gateway.host;
        if (this.gateway.id !== undefined) {
            this.subscribeToSaveResponse(this.gatewayService.update(this.gateway));
        } else {
            this.subscribeToSaveResponse(this.gatewayService.create(this.gateway));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGateway>>) {
        result.subscribe((res: HttpResponse<IGateway>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackDialPlanById(index: number, item: IDialPlan) {
        return item.id;
    }
}
