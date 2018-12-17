/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { ActiveCallComponent } from 'app/entities/active-call/active-call.component';
import { ActiveCallService } from 'app/entities/active-call/active-call.service';
import { ActiveCall } from 'app/shared/model/active-call.model';

describe('Component Tests', () => {
    describe('ActiveCall Management Component', () => {
        let comp: ActiveCallComponent;
        let fixture: ComponentFixture<ActiveCallComponent>;
        let service: ActiveCallService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [ActiveCallComponent],
                providers: []
            })
                .overrideTemplate(ActiveCallComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ActiveCallComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActiveCallService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ActiveCall(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.activeCalls[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
