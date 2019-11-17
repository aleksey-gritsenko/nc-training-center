import {Id} from '../models/id'

export class Book extends Id{
  header: string;
  authors:string[];
  overview: string;
  photo:string;
  file:number;
  status: string;
  genres:string[];
}

