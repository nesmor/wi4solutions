/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { SipPeerDeleteDialogComponent } from 'app/entities/sip-peer/sip-peer-delete-dialog.component';
import { SipPeerService } from 'app/entities/sip-peer/sip-peer.service';

describe('Component Tests', () => {
    describe('SipPeer Management Delete Component', () => {
        let comp: SipPeerDeleteDialogComponent;
        let fixture: ComponentFixture<SipPeerDeleteDialogComponent>;
        let service: SipPeerService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [SipPeerDeleteDialogComponent]
            })
                .overrideTemplate(SipPeerDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SipPeerDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SipPeerService);
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
