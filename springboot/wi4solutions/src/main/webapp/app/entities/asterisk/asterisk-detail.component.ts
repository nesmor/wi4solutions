import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAsterisk } from 'app/shared/model/asterisk.model';

@Component({
    selector: 'jhi-asterisk-detail',
    templateUrl: './asterisk-detail.component.html'
})
export class AsteriskDetailComponent implements OnInit {
    asterisk: IAsterisk;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ asterisk }) => {
            this.asterisk = asterisk;
        });
    }

    previousState() {
        window.history.back();
    }
}
