/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { AsteriskUpdateComponent } from 'app/entities/asterisk/asterisk-update.component';
import { AsteriskService } from 'app/entities/asterisk/asterisk.service';
import { Asterisk } from 'app/shared/model/asterisk.model';

describe('Component Tests', () => {
    describe('Asterisk Management Update Component', () => {
        let comp: AsteriskUpdateComponent;
        let fixture: ComponentFixture<AsteriskUpdateComponent>;
        let service: AsteriskService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [AsteriskUpdateComponent]
            })
                .overrideTemplate(AsteriskUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AsteriskUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AsteriskService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Asterisk();
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.asterisk = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Asterisk();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.asterisk = entity;
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
