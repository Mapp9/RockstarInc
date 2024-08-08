import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { TopnavComponent } from './components/topnav/topnav.component';
import { FooterComponent } from './components/footer/footer.component';

export const routes: Routes = [
    {path: '', component: HomeComponent}
];
