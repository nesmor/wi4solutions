/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { AsteriskComponent } from 'app/entities/asterisk/asterisk.component';
import { AsteriskService } from 'app/entities/asterisk/asterisk.service';
import { Asterisk } from 'app/shared/model/asterisk.model';

describe('Component Tests', () => {
    describe('Asterisk Management Component', () => {
        let comp: AsteriskComponent;
        let fixture: ComponentFixture<AsteriskComponent>;
        let service: AsteriskService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [AsteriskComponent],
                providers: []
            })
                .overrideTemplate(AsteriskComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AsteriskComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AsteriskService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Asterisk()],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.asterisks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
