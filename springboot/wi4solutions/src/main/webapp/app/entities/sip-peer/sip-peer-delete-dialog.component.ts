import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISipPeer } from 'app/shared/model/sip-peer.model';
import { SipPeerService } from './sip-peer.service';

@Component({
    selector: 'jhi-sip-peer-delete-dialog',
    templateUrl: './sip-peer-delete-dialog.component.html'
})
export class SipPeerDeleteDialogComponent {
    sipPeer: ISipPeer;

    constructor(private sipPeerService: SipPeerService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sipPeerService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sipPeerListModification',
                content: 'Deleted an sipPeer'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sip-peer-delete-popup',
    template: ''
})
export class SipPeerDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sipPeer }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SipPeerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sipPeer = sipPeer;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
