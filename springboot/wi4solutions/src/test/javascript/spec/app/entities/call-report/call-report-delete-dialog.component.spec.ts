/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { CallReportDeleteDialogComponent } from 'app/entities/call-report/call-report-delete-dialog.component';
import { CallReportService } from 'app/entities/call-report/call-report.service';

describe('Component Tests', () => {
    describe('CallReport Management Delete Component', () => {
        let comp: CallReportDeleteDialogComponent;
        let fixture: ComponentFixture<CallReportDeleteDialogComponent>;
        let service: CallReportService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [CallReportDeleteDialogComponent]
            })
                .overrideTemplate(CallReportDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CallReportDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CallReportService);
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
