import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IActiveCall } from 'app/shared/model/active-call.model';
import { ActiveCallService } from './active-call.service';

@Component({
    selector: 'jhi-active-call-delete-dialog',
    templateUrl: './active-call-delete-dialog.component.html'
})
export class ActiveCallDeleteDialogComponent {
    activeCall: IActiveCall;

    constructor(private activeCallService: ActiveCallService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.activeCallService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'activeCallListModification',
                content: 'Deleted an activeCall'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-active-call-delete-popup',
    template: ''
})
export class ActiveCallDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ activeCall }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ActiveCallDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.activeCall = activeCall;
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
