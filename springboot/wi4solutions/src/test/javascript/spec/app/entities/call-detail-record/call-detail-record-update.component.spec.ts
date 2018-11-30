/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { CallDetailRecordUpdateComponent } from 'app/entities/call-detail-record/call-detail-record-update.component';
import { CallDetailRecordService } from 'app/entities/call-detail-record/call-detail-record.service';
import { CallDetailRecord } from 'app/shared/model/call-detail-record.model';

describe('Component Tests', () => {
    describe('CallDetailRecord Management Update Component', () => {
        let comp: CallDetailRecordUpdateComponent;
        let fixture: ComponentFixture<CallDetailRecordUpdateComponent>;
        let service: CallDetailRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [CallDetailRecordUpdateComponent]
            })
                .overrideTemplate(CallDetailRecordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CallDetailRecordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CallDetailRecordService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CallDetailRecord(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.callDetailRecord = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CallDetailRecord();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.callDetailRecord = entity;
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
