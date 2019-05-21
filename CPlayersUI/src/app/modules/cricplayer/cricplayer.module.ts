import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AngularmaterialModule } from '../angularmaterial/angularmaterial.module';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { CardComponent } from './components/card/card.component';
import { CardContainerComponent } from './components/card-container/card-container.component';
import { CardServiceService } from './service/card-service.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from './interceptor.service';
import { FavoriteComponent } from './components/favorite/favorite.component';
import { RecommendedComponent } from './components/recommended/recommended.component';
import { DetailsComponent } from './components/details/details/details.component';

@NgModule({
  declarations: [CardContainerComponent, CardComponent, HeaderComponent, FavoriteComponent, FooterComponent, RecommendedComponent, DetailsComponent],
  imports: [
    AngularmaterialModule,
    FormsModule,
    CommonModule,
    BrowserAnimationsModule,
    AppRoutingModule,
  ],
  exports: [
    CardContainerComponent,
    CardComponent,
    HeaderComponent,
    FavoriteComponent,
    FooterComponent,
    RecommendedComponent,
    DetailsComponent
  ],
  providers: [
    CardServiceService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true

    }
  ],
})
export class CricplayerModule { }
