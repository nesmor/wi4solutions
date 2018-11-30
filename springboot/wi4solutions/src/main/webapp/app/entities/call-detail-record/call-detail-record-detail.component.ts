import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICallDetailRecord } from 'app/shared/model/call-detail-record.model';

@Component({
    selector: 'jhi-call-detail-record-detail',
    templateUrl: './call-detail-record-detail.component.html'
})
export class CallDetailRecordDetailComponent implements OnInit {
    callDetailRecord: ICallDetailRecord;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ callDetailRecord }) => {
            this.callDetailRecord = callDetailRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
