import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAsterisk } from 'app/shared/model/asterisk.model';
import { AsteriskService } from './asterisk.service';

@Component({
    selector: 'jhi-asterisk-delete-dialog',
    templateUrl: './asterisk-delete-dialog.component.html'
})
export class AsteriskDeleteDialogComponent {
    asterisk: IAsterisk;

    constructor(private asteriskService: AsteriskService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.asteriskService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'asteriskListModification',
                content: 'Deleted an asterisk'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-asterisk-delete-popup',
    template: ''
})
export class AsteriskDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ asterisk }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AsteriskDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.asterisk = asterisk;
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
