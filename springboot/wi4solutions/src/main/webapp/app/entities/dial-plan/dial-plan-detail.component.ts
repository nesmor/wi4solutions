import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDialPlan } from 'app/shared/model/dial-plan.model';

@Component({
    selector: 'jhi-dial-plan-detail',
    templateUrl: './dial-plan-detail.component.html'
})
export class DialPlanDetailComponent implements OnInit {
    dialPlan: IDialPlan;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dialPlan }) => {
            this.dialPlan = dialPlan;
        });
    }

    previousState() {
        window.history.back();
    }
}
