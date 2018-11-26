/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { DialPlanComponent } from 'app/entities/dial-plan/dial-plan.component';
import { DialPlanService } from 'app/entities/dial-plan/dial-plan.service';
import { DialPlan } from 'app/shared/model/dial-plan.model';

describe('Component Tests', () => {
    describe('DialPlan Management Component', () => {
        let comp: DialPlanComponent;
        let fixture: ComponentFixture<DialPlanComponent>;
        let service: DialPlanService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [DialPlanComponent],
                providers: []
            })
                .overrideTemplate(DialPlanComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DialPlanComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DialPlanService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DialPlan(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.dialPlans[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
