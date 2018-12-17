import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IActiveCall } from 'app/shared/model/active-call.model';
import { ActiveCallService } from './active-call.service';

@Component({
    selector: 'jhi-active-call-update',
    templateUrl: './active-call-update.component.html'
})
export class ActiveCallUpdateComponent implements OnInit {
    activeCall: IActiveCall;
    isSaving: boolean;

    constructor(private activeCallService: ActiveCallService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ activeCall }) => {
            this.activeCall = activeCall;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.activeCall.id !== undefined) {
            this.subscribeToSaveResponse(this.activeCallService.update(this.activeCall));
        } else {
            this.subscribeToSaveResponse(this.activeCallService.create(this.activeCall));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IActiveCall>>) {
        result.subscribe((res: HttpResponse<IActiveCall>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
