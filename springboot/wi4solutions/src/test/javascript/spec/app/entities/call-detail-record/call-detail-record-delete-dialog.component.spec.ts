/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { CallDetailRecordDeleteDialogComponent } from 'app/entities/call-detail-record/call-detail-record-delete-dialog.component';
import { CallDetailRecordService } from 'app/entities/call-detail-record/call-detail-record.service';

describe('Component Tests', () => {
    describe('CallDetailRecord Management Delete Component', () => {
        let comp: CallDetailRecordDeleteDialogComponent;
        let fixture: ComponentFixture<CallDetailRecordDeleteDialogComponent>;
        let service: CallDetailRecordService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [CallDetailRecordDeleteDialogComponent]
            })
                .overrideTemplate(CallDetailRecordDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CallDetailRecordDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CallDetailRecordService);
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
