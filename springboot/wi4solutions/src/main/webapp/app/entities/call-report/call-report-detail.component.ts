import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICallReport } from 'app/shared/model/call-report.model';

@Component({
    selector: 'jhi-call-report-detail',
    templateUrl: './call-report-detail.component.html'
})
export class CallReportDetailComponent implements OnInit {
    callReport: ICallReport;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ callReport }) => {
            this.callReport = callReport;
        });
    }

    previousState() {
        window.history.back();
    }
}
