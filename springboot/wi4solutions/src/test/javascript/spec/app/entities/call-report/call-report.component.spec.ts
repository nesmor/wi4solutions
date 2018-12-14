/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { CallReportComponent } from 'app/entities/call-report/call-report.component';
import { CallReportService } from 'app/entities/call-report/call-report.service';
import { CallReport } from 'app/shared/model/call-report.model';

describe('Component Tests', () => {
    describe('CallReport Management Component', () => {
        let comp: CallReportComponent;
        let fixture: ComponentFixture<CallReportComponent>;
        let service: CallReportService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [CallReportComponent],
                providers: []
            })
                .overrideTemplate(CallReportComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CallReportComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CallReportService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CallReport(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.callReports[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
