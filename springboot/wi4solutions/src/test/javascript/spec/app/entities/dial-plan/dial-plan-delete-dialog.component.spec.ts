/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { DialPlanDeleteDialogComponent } from 'app/entities/dial-plan/dial-plan-delete-dialog.component';
import { DialPlanService } from 'app/entities/dial-plan/dial-plan.service';

describe('Component Tests', () => {
    describe('DialPlan Management Delete Component', () => {
        let comp: DialPlanDeleteDialogComponent;
        let fixture: ComponentFixture<DialPlanDeleteDialogComponent>;
        let service: DialPlanService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [DialPlanDeleteDialogComponent]
            })
                .overrideTemplate(DialPlanDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DialPlanDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DialPlanService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
