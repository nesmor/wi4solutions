/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { SipPeerDetailComponent } from 'app/entities/sip-peer/sip-peer-detail.component';
import { SipPeer } from 'app/shared/model/sip-peer.model';

describe('Component Tests', () => {
    describe('SipPeer Management Detail Component', () => {
        let comp: SipPeerDetailComponent;
        let fixture: ComponentFixture<SipPeerDetailComponent>;
        const route = ({ data: of({ sipPeer: new SipPeer(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [SipPeerDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SipPeerDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SipPeerDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sipPeer).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
