/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { AsteriskDetailComponent } from 'app/entities/asterisk/asterisk-detail.component';
import { Asterisk } from 'app/shared/model/asterisk.model';

describe('Component Tests', () => {
    describe('Asterisk Management Detail Component', () => {
        let comp: AsteriskDetailComponent;
        let fixture: ComponentFixture<AsteriskDetailComponent>;
        const route = ({ data: of({ asterisk: new Asterisk() }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [AsteriskDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AsteriskDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AsteriskDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.asterisk).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
