--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: agenda; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE agenda WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'pt_BR.UTF-8';


ALTER DATABASE agenda OWNER TO postgres;

\connect agenda

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: tema10; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA tema10;


ALTER SCHEMA tema10 OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: agtb_contato; Type: TABLE; Schema: tema10; Owner: postgres
--

CREATE TABLE tema10.agtb_contato (
    id_contato integer NOT NULL,
    nome character varying(100),
    telefone character varying(20),
    email character varying(200)
);


ALTER TABLE tema10.agtb_contato OWNER TO postgres;

--
-- Name: agsq_contato; Type: SEQUENCE; Schema: tema10; Owner: postgres
--

CREATE SEQUENCE tema10.agsq_contato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tema10.agsq_contato OWNER TO postgres;

--
-- Name: agsq_contato; Type: SEQUENCE OWNED BY; Schema: tema10; Owner: postgres
--

ALTER SEQUENCE tema10.agsq_contato OWNED BY tema10.agtb_contato.id_contato;


--
-- Name: agtb_contato id_contato; Type: DEFAULT; Schema: tema10; Owner: postgres
--

ALTER TABLE ONLY tema10.agtb_contato ALTER COLUMN id_contato SET DEFAULT nextval('tema10.agsq_contato'::regclass);


--
-- Data for Name: agtb_contato; Type: TABLE DATA; Schema: tema10; Owner: postgres
--

COPY tema10.agtb_contato (id_contato, nome, telefone, email) FROM stdin;
\.


--
-- Name: agsq_contato; Type: SEQUENCE SET; Schema: tema10; Owner: postgres
--

SELECT pg_catalog.setval('tema10.agsq_contato', 1, false);


--
-- Name: agtb_contato agpk_contato; Type: CONSTRAINT; Schema: tema10; Owner: postgres
--

ALTER TABLE ONLY tema10.agtb_contato
    ADD CONSTRAINT agpk_contato PRIMARY KEY (id_contato);


--
-- PostgreSQL database dump complete
--

