import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class SearchService {

    // private searchUrl = '/search';
    // private moderatorsUrl = '/moderators';
    // private adminsUrl = '/admin';
    // private superAdmin = '/superadmin';

    constructor(private http: HttpClient) {
    }

    search(value: string) {
        const url = `/search/${value}`;
        this.http.get(url);
    }

    searchProfile(profile: string) {

        // switch(profile){
        //   case 'superadmin':
        //     return this.http.get(this.superAdmin);
        //   case 'admins':
        //     return this.http.get(this.adminsUrl);
        //   case 'moderators':
        //     return this.http.get(this.moderatorsUrl);
        //   // search specific admin/moderator by value

        //   // ...............................

        // }
    }

}
