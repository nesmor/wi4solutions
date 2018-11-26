import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDialPlan } from 'app/shared/model/dial-plan.model';
import { DialPlanService } from './dial-plan.service';
import { ISipPeer } from 'app/shared/model/sip-peer.model';
import { SipPeerService } from 'app/entities/sip-peer';

@Component({
    selector: 'jhi-dial-plan-update',
    templateUrl: './dial-plan-update.component.html'
})
export class DialPlanUpdateComponent implements OnInit {
    dialPlan: IDialPlan;
    isSaving: boolean;

    sippeers: ISipPeer[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private dialPlanService: DialPlanService,
        private sipPeerService: SipPeerService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dialPlan }) => {
            this.dialPlan = dialPlan;
        });
        this.sipPeerService.query({ filter: 'dialplan-is-null' }).subscribe(
            (res: HttpResponse<ISipPeer[]>) => {
                if (!this.dialPlan.sipPeer || !this.dialPlan.sipPeer.id) {
                    this.sippeers = res.body;
                } else {
                    this.sipPeerService.find(this.dialPlan.sipPeer.id).subscribe(
                        (subRes: HttpResponse<ISipPeer>) => {
                            this.sippeers = [subRes.body].concat(res.body);
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

    trackSipPeerById(index: number, item: ISipPeer) {
        return item.id;
    }
}
