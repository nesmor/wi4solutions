/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { AsteriskDeleteDialogComponent } from 'app/entities/asterisk/asterisk-delete-dialog.component';
import { AsteriskService } from 'app/entities/asterisk/asterisk.service';

describe('Component Tests', () => {
    describe('Asterisk Management Delete Component', () => {
        let comp: AsteriskDeleteDialogComponent;
        let fixture: ComponentFixture<AsteriskDeleteDialogComponent>;
        let service: AsteriskService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [AsteriskDeleteDialogComponent]
            })
                .overrideTemplate(AsteriskDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AsteriskDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AsteriskService);
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
