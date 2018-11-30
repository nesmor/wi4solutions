import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICallDetailRecord } from 'app/shared/model/call-detail-record.model';
import { CallDetailRecordService } from './call-detail-record.service';

@Component({
    selector: 'jhi-call-detail-record-delete-dialog',
    templateUrl: './call-detail-record-delete-dialog.component.html'
})
export class CallDetailRecordDeleteDialogComponent {
    callDetailRecord: ICallDetailRecord;

    constructor(
        private callDetailRecordService: CallDetailRecordService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.callDetailRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'callDetailRecordListModification',
                content: 'Deleted an callDetailRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-call-detail-record-delete-popup',
    template: ''
})
export class CallDetailRecordDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ callDetailRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CallDetailRecordDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.callDetailRecord = callDetailRecord;
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
