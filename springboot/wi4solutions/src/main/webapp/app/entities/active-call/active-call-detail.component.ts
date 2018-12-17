import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IActiveCall } from 'app/shared/model/active-call.model';

@Component({
    selector: 'jhi-active-call-detail',
    templateUrl: './active-call-detail.component.html'
})
export class ActiveCallDetailComponent implements OnInit {
    activeCall: IActiveCall;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ activeCall }) => {
            this.activeCall = activeCall;
        });
    }

    previousState() {
        window.history.back();
    }
}
