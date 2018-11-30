import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISipPeer } from 'app/shared/model/sip-peer.model';
import { SipPeerService } from './sip-peer.service';

@Component({
    selector: 'jhi-sip-peer-update',
    templateUrl: './sip-peer-update.component.html'
})
export class SipPeerUpdateComponent implements OnInit {
    sipPeer: ISipPeer;
    isSaving: boolean;

    constructor(private sipPeerService: SipPeerService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sipPeer }) => {
            this.sipPeer = sipPeer;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sipPeer.nat = 'force_rport';
        this.sipPeer.username = this.sipPeer.host;
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
}
