/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { CallReportDetailComponent } from 'app/entities/call-report/call-report-detail.component';
import { CallReport } from 'app/shared/model/call-report.model';

describe('Component Tests', () => {
    describe('CallReport Management Detail Component', () => {
        let comp: CallReportDetailComponent;
        let fixture: ComponentFixture<CallReportDetailComponent>;
        const route = ({ data: of({ callReport: new CallReport(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [CallReportDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CallReportDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CallReportDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.callReport).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
