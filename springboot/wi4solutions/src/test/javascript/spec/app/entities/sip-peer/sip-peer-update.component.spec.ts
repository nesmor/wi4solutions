/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { SipPeerUpdateComponent } from 'app/entities/sip-peer/sip-peer-update.component';
import { SipPeerService } from 'app/entities/sip-peer/sip-peer.service';
import { SipPeer } from 'app/shared/model/sip-peer.model';

describe('Component Tests', () => {
    describe('SipPeer Management Update Component', () => {
        let comp: SipPeerUpdateComponent;
        let fixture: ComponentFixture<SipPeerUpdateComponent>;
        let service: SipPeerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [SipPeerUpdateComponent]
            })
                .overrideTemplate(SipPeerUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SipPeerUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SipPeerService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SipPeer(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sipPeer = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SipPeer();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sipPeer = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
