/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { ActiveCallUpdateComponent } from 'app/entities/active-call/active-call-update.component';
import { ActiveCallService } from 'app/entities/active-call/active-call.service';
import { ActiveCall } from 'app/shared/model/active-call.model';

describe('Component Tests', () => {
    describe('ActiveCall Management Update Component', () => {
        let comp: ActiveCallUpdateComponent;
        let fixture: ComponentFixture<ActiveCallUpdateComponent>;
        let service: ActiveCallService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [ActiveCallUpdateComponent]
            })
                .overrideTemplate(ActiveCallUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ActiveCallUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActiveCallService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ActiveCall(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.activeCall = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ActiveCall();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.activeCall = entity;
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
