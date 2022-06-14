export interface CardData {
  author: string|null,
  content: string | null,
  description: string | null,
  publishedAt: Date | string | null,
  source : {id : string | null, name : string | null} | null,
  title: string | null,
  url: string | null,
  urlToImage: string | null,
}
