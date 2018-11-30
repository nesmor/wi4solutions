/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { DialPlanUpdateComponent } from 'app/entities/dial-plan/dial-plan-update.component';
import { DialPlanService } from 'app/entities/dial-plan/dial-plan.service';
import { DialPlan } from 'app/shared/model/dial-plan.model';

describe('Component Tests', () => {
    describe('DialPlan Management Update Component', () => {
        let comp: DialPlanUpdateComponent;
        let fixture: ComponentFixture<DialPlanUpdateComponent>;
        let service: DialPlanService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [DialPlanUpdateComponent]
            })
                .overrideTemplate(DialPlanUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DialPlanUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DialPlanService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DialPlan(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dialPlan = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DialPlan();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dialPlan = entity;
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
