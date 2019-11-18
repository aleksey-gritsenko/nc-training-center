import {Id} from './id'

export class Book extends Id{
  header: string;
  authors:string[];
  overview: string;
  photoId:string;
  fileId:number;
  status: string;
  genres:string[];
}

