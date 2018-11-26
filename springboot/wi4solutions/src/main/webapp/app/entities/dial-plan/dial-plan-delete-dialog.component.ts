import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDialPlan } from 'app/shared/model/dial-plan.model';
import { DialPlanService } from './dial-plan.service';

@Component({
    selector: 'jhi-dial-plan-delete-dialog',
    templateUrl: './dial-plan-delete-dialog.component.html'
})
export class DialPlanDeleteDialogComponent {
    dialPlan: IDialPlan;

    constructor(private dialPlanService: DialPlanService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dialPlanService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dialPlanListModification',
                content: 'Deleted an dialPlan'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dial-plan-delete-popup',
    template: ''
})
export class DialPlanDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dialPlan }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DialPlanDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.dialPlan = dialPlan;
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
