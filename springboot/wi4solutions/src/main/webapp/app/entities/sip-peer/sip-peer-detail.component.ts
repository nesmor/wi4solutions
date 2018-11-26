import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISipPeer } from 'app/shared/model/sip-peer.model';

@Component({
    selector: 'jhi-sip-peer-detail',
    templateUrl: './sip-peer-detail.component.html'
})
export class SipPeerDetailComponent implements OnInit {
    sipPeer: ISipPeer;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sipPeer }) => {
            this.sipPeer = sipPeer;
        });
    }

    previousState() {
        window.history.back();
    }
}
