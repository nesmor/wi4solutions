/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { SipPeerComponent } from 'app/entities/sip-peer/sip-peer.component';
import { SipPeerService } from 'app/entities/sip-peer/sip-peer.service';
import { SipPeer } from 'app/shared/model/sip-peer.model';

describe('Component Tests', () => {
    describe('SipPeer Management Component', () => {
        let comp: SipPeerComponent;
        let fixture: ComponentFixture<SipPeerComponent>;
        let service: SipPeerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [SipPeerComponent],
                providers: []
            })
                .overrideTemplate(SipPeerComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SipPeerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SipPeerService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SipPeer(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sipPeers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
