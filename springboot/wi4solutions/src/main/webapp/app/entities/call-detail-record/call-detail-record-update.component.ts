import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICallDetailRecord } from 'app/shared/model/call-detail-record.model';
import { CallDetailRecordService } from './call-detail-record.service';

@Component({
    selector: 'jhi-call-detail-record-update',
    templateUrl: './call-detail-record-update.component.html'
})
export class CallDetailRecordUpdateComponent implements OnInit {
    callDetailRecord: ICallDetailRecord;
    isSaving: boolean;
    calldate: string;

    constructor(private callDetailRecordService: CallDetailRecordService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ callDetailRecord }) => {
            this.callDetailRecord = callDetailRecord;
            this.calldate = this.callDetailRecord.calldate != null ? this.callDetailRecord.calldate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.callDetailRecord.calldate = this.calldate != null ? moment(this.calldate, DATE_TIME_FORMAT) : null;
        if (this.callDetailRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.callDetailRecordService.update(this.callDetailRecord));
        } else {
            this.subscribeToSaveResponse(this.callDetailRecordService.create(this.callDetailRecord));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICallDetailRecord>>) {
        result.subscribe((res: HttpResponse<ICallDetailRecord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
