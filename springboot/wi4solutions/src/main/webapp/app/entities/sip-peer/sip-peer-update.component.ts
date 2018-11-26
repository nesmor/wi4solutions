import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISipPeer } from 'app/shared/model/sip-peer.model';
import { SipPeerService } from './sip-peer.service';
import { IDialPlan } from 'app/shared/model/dial-plan.model';
import { DialPlanService } from 'app/entities/dial-plan';

@Component({
    selector: 'jhi-sip-peer-update',
    templateUrl: './sip-peer-update.component.html'
})
export class SipPeerUpdateComponent implements OnInit {
    sipPeer: ISipPeer;
    isSaving: boolean;

    dialplans: IDialPlan[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private sipPeerService: SipPeerService,
        private dialPlanService: DialPlanService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sipPeer }) => {
            this.sipPeer = sipPeer;
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
        if (this.sipPeer.id !== undefined) {
            this.subscribeToSaveResponse(this.sipPeerService.update(this.sipPeer));
        } else {
            this.subscribeToSaveResponse(this.sipPeerService.create(this.sipPeer));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISipPeer>>) {
        result.subscribe((res: HttpResponse<ISipPeer>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
