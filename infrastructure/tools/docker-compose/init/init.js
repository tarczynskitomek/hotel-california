db.createUser({
  user: "hcadmin",
  pwd: "password",
  roles: [{role: "dbOwner", db: "hotelcalifornia"}]
});
