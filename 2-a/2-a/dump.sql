--
-- PostgreSQL database dump
--

\restrict t2cCRkyESaftCRsAb3xwf7Es1dyND4xTkxhIxJlWguY3ImnQJprON9HwSv7GEGT

-- Dumped from database version 16.13
-- Dumped by pg_dump version 16.13

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: contatos; Type: TABLE; Schema: public; Owner: agenda_user
--

CREATE TABLE public.contatos (
    id bigint NOT NULL,
    email character varying(200),
    nome character varying(150) NOT NULL,
    telefone character varying(20) NOT NULL
);


ALTER TABLE public.contatos OWNER TO agenda_user;

--
-- Name: contatos_id_seq; Type: SEQUENCE; Schema: public; Owner: agenda_user
--

CREATE SEQUENCE public.contatos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.contatos_id_seq OWNER TO agenda_user;

--
-- Name: contatos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: agenda_user
--

ALTER SEQUENCE public.contatos_id_seq OWNED BY public.contatos.id;


--
-- Name: contatos id; Type: DEFAULT; Schema: public; Owner: agenda_user
--

ALTER TABLE ONLY public.contatos ALTER COLUMN id SET DEFAULT nextval('public.contatos_id_seq'::regclass);


--
-- Data for Name: contatos; Type: TABLE DATA; Schema: public; Owner: agenda_user
--

COPY public.contatos (id, email, nome, telefone) FROM stdin;
1	ana.souza@email.com	Ana Souza	(11) 91234-5678
2	bruno.lima@email.com	Bruno Lima	(21) 98765-4321
3	carla.mendes@email.com	Carla Mendes	(31) 99876-5432
4	diego.ferreira@email.com	Diego Ferreira	(41) 97654-3210
5	eduarda.costa@email.com	Eduarda Costa	(51) 96543-2109
6	felipe.rocha@email.com	Felipe Rocha	(61) 95432-1098
7	gabriela.nunes@email.com	Gabriela Nunes	(71) 94321-0987
8	henrique.alves@email.com	Henrique Alves	(81) 93210-9876
9	isabela.cardoso@email.com	Isabela Cardoso	(91) 92109-8765
10	joao.pereira@email.com	Joao Pereira	(11) 91098-7654
11	karen.oliveira@email.com	Karen Oliveira	(21) 90987-6543
12	lucas.martins@email.com	Lucas Martins	(31) 99876-5431
13	mariana.santos@email.com	Mariana Santos	(41) 98765-4320
14	nicolas.barbosa@email.com	Nicolas Barbosa	(51) 97654-3209
15	patricia.lima@email.com	Patricia Lima	(61) 96543-2108
\.


--
-- Name: contatos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: agenda_user
--

SELECT pg_catalog.setval('public.contatos_id_seq', 15, true);


--
-- Name: contatos contatos_pkey; Type: CONSTRAINT; Schema: public; Owner: agenda_user
--

ALTER TABLE ONLY public.contatos
    ADD CONSTRAINT contatos_pkey PRIMARY KEY (id);


--
-- Name: contatos uk_ll1e1fbuxkk79hl65gh0doraq; Type: CONSTRAINT; Schema: public; Owner: agenda_user
--

ALTER TABLE ONLY public.contatos
    ADD CONSTRAINT uk_ll1e1fbuxkk79hl65gh0doraq UNIQUE (nome);


--
-- PostgreSQL database dump complete
--

\unrestrict t2cCRkyESaftCRsAb3xwf7Es1dyND4xTkxhIxJlWguY3ImnQJprON9HwSv7GEGT

