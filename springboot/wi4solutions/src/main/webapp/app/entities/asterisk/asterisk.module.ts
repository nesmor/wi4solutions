import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Wi4SolutionsSharedModule } from 'app/shared';
import {
    AsteriskComponent,
    AsteriskDetailComponent,
    AsteriskUpdateComponent,
    AsteriskDeletePopupComponent,
    AsteriskDeleteDialogComponent,
    asteriskRoute,
    asteriskPopupRoute
} from './';

const ENTITY_STATES = [...asteriskRoute, ...asteriskPopupRoute];

@NgModule({
    imports: [Wi4SolutionsSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AsteriskComponent,
        AsteriskDetailComponent,
        AsteriskUpdateComponent,
        AsteriskDeleteDialogComponent,
        AsteriskDeletePopupComponent
    ],
    entryComponents: [AsteriskComponent, AsteriskUpdateComponent, AsteriskDeleteDialogComponent, AsteriskDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Wi4SolutionsAsteriskModule {}
