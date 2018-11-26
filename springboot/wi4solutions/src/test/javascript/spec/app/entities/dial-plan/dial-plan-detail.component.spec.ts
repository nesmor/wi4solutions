/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { DialPlanDetailComponent } from 'app/entities/dial-plan/dial-plan-detail.component';
import { DialPlan } from 'app/shared/model/dial-plan.model';

describe('Component Tests', () => {
    describe('DialPlan Management Detail Component', () => {
        let comp: DialPlanDetailComponent;
        let fixture: ComponentFixture<DialPlanDetailComponent>;
        const route = ({ data: of({ dialPlan: new DialPlan(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [DialPlanDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DialPlanDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DialPlanDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dialPlan).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
