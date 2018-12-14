import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICallReport } from 'app/shared/model/call-report.model';
import { CallReportService } from './call-report.service';

@Component({
    selector: 'jhi-call-report-update',
    templateUrl: './call-report-update.component.html'
})
export class CallReportUpdateComponent implements OnInit {
    callReport: ICallReport;
    isSaving: boolean;

    constructor(private callReportService: CallReportService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ callReport }) => {
            this.callReport = callReport;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.callReport.id !== undefined) {
            this.subscribeToSaveResponse(this.callReportService.update(this.callReport));
        } else {
            this.subscribeToSaveResponse(this.callReportService.create(this.callReport));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICallReport>>) {
        result.subscribe((res: HttpResponse<ICallReport>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
