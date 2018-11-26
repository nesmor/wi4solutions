import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Wi4SolutionsSharedModule } from 'app/shared';
import {
    SipPeerComponent,
    SipPeerDetailComponent,
    SipPeerUpdateComponent,
    SipPeerDeletePopupComponent,
    SipPeerDeleteDialogComponent,
    sipPeerRoute,
    sipPeerPopupRoute
} from './';

const ENTITY_STATES = [...sipPeerRoute, ...sipPeerPopupRoute];

@NgModule({
    imports: [Wi4SolutionsSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SipPeerComponent,
        SipPeerDetailComponent,
        SipPeerUpdateComponent,
        SipPeerDeleteDialogComponent,
        SipPeerDeletePopupComponent
    ],
    entryComponents: [SipPeerComponent, SipPeerUpdateComponent, SipPeerDeleteDialogComponent, SipPeerDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Wi4SolutionsSipPeerModule {}
