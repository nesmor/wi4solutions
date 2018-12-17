/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { ActiveCallDeleteDialogComponent } from 'app/entities/active-call/active-call-delete-dialog.component';
import { ActiveCallService } from 'app/entities/active-call/active-call.service';

describe('Component Tests', () => {
    describe('ActiveCall Management Delete Component', () => {
        let comp: ActiveCallDeleteDialogComponent;
        let fixture: ComponentFixture<ActiveCallDeleteDialogComponent>;
        let service: ActiveCallService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [ActiveCallDeleteDialogComponent]
            })
                .overrideTemplate(ActiveCallDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ActiveCallDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActiveCallService);
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
