import {Id} from '../models/id'

export class Book extends Id{
  title: string;
  header: string;
  author:string;
  overview: string; // maybe Text?
  photo:number;
  file:number;
  genre: string; // Double or Int
  status: string;
}

