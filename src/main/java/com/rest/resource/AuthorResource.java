package com.rest.resource;

import com.rest.model.Author;
import org.skife.jdbi.v2.Handle;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final Handle handle;

    public AuthorResource(Handle handle) {
        this.handle = handle;
    }

    @POST
    @Path("/{authorId}.json")
    public Response post(@PathParam("authorId")Integer authorId, Author author) {
        handle.insert("insert into authors values(?,?)", authorId, author.getName());
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{authorId}.json")
    public Response put(@PathParam("authorId")Integer authorId, Author author) {
        handle.insert("update authors set name = ? where id = ?", author.getName(), authorId);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{authorId}.json")
    public Response delete(@PathParam("authorId")Integer authorId) {
        handle.insert("delete from authors where id = ?", authorId);
        return Response.status(Response.Status.OK).build();
    }


}