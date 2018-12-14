/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { CallReportUpdateComponent } from 'app/entities/call-report/call-report-update.component';
import { CallReportService } from 'app/entities/call-report/call-report.service';
import { CallReport } from 'app/shared/model/call-report.model';

describe('Component Tests', () => {
    describe('CallReport Management Update Component', () => {
        let comp: CallReportUpdateComponent;
        let fixture: ComponentFixture<CallReportUpdateComponent>;
        let service: CallReportService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [CallReportUpdateComponent]
            })
                .overrideTemplate(CallReportUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CallReportUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CallReportService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CallReport(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.callReport = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CallReport();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.callReport = entity;
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
