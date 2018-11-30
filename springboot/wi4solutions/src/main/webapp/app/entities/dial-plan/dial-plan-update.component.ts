import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDialPlan } from 'app/shared/model/dial-plan.model';
import { DialPlanService } from './dial-plan.service';
import { IGateway } from 'app/shared/model/gateway.model';
import { GatewayService } from 'app/entities/gateway';

@Component({
    selector: 'jhi-dial-plan-update',
    templateUrl: './dial-plan-update.component.html'
})
export class DialPlanUpdateComponent implements OnInit {
    dialPlan: IDialPlan;
    isSaving: boolean;

    gateways: IGateway[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private dialPlanService: DialPlanService,
        private gatewayService: GatewayService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dialPlan }) => {
            this.dialPlan = dialPlan;
        });
        this.gatewayService.query().subscribe(
            // this.gatewayService.query({ filter: 'dialplan-is-null' }).subscribe(
            (res: HttpResponse<IGateway[]>) => {
                if (!this.dialPlan.gateway || !this.dialPlan.gateway.id) {
                    this.gateways = res.body;
                } else {
                    this.gatewayService.find(this.dialPlan.gateway.id).subscribe(
                        (subRes: HttpResponse<IGateway>) => {
                            this.gateways = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.dialPlan.id !== undefined) {
            this.subscribeToSaveResponse(this.dialPlanService.update(this.dialPlan));
        } else {
            this.subscribeToSaveResponse(this.dialPlanService.create(this.dialPlan));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDialPlan>>) {
        result.subscribe((res: HttpResponse<IDialPlan>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackGatewayById(index: number, item: IGateway) {
        return item.id;
    }
}
